<template>
  <q-page class="q-pa-lg">
    <p>Siga os passos abaixo para marcar uma nova consulta</p>
    <q-stepper v-model="step" vertical color="primary" animated flat style="max-width: 800px;">
      <q-step :name="1" title="Selecione um médico" icon="eva-people-outline" :done="step > 1">
        Selecione um médico para o atendimento
        <q-table v-model:selected="form.doctor" v-model:pagination="pagination" :columns="columns"
          :rows="$appointmentStore.doctors" :rows-per-page-options="[4]" row-key="id" selection="single"
          class="table-doctors" :filter="filterDoctor" virtual-scroll flat>
          <template v-slot:top-left>
            <q-input borderless dense debounce="300" v-model="filterDoctor" placeholder="Nome do médico">
              <template v-slot:prepend>
                <q-icon name="eva-search-outline" />
              </template>
            </q-input>
          </template>
        </q-table>
        <q-stepper-navigation>
          <q-btn @click="step = 2" color="primary" label="Continue" :disable="shouldEnableDoctorNextButton" />
        </q-stepper-navigation>
      </q-step>

      <q-step :name="2" title="Selecione uma data" icon="eva-clock-outline" :done="step > 2">
        Selecione um data para a consulta
        <div class="flex q-gutter-md">
          <q-input v-model="form.date" label="data" mask="##/##/####" filled>
            <template v-slot:append>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                  <q-date v-model="form.date" mask="DD/MM/YYYY" :options="dateOptions">
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="Confirmar" color="primary" flat />
                    </div>
                  </q-date>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>

          <q-input filled v-model="form.time" label="hora" mask="time" :rules="['time']">
            <template v-slot:append>
              <q-icon name="access_time" class="cursor-pointer">
                <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                  <q-time v-model="form.time" format24h>
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="Confirmar" color="primary" flat />
                    </div>
                  </q-time>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </div>
        <q-stepper-navigation>
          <q-btn flat @click="step = 1" color="primary" label="Voltar" class="q-ml-sm" />
          <q-btn @click="step = 3" color="primary" label="Continue" :disable="shouldEnableDateNextButton" />
        </q-stepper-navigation>
      </q-step>

      <q-step :name="3" title="Revisão" icon="eva-clipboard-outline" :done="step > 3">
        Por favor, verifique se os dados estão corretos:
        <div>
          <div class="q-mb-md">
            <div class="text-h6">{{ form.doctor[0].name }}</div>
            <div class="text-subtitle2 text-grey">Médico</div>
          </div>
          <div>
            <div class="text-h6">{{ form.date }} às {{ form.time }}</div>
            <div class="text-subtitle2 text-grey">Data e hora</div>
          </div>
        </div>

        <q-stepper-navigation>
          <q-btn flat @click="step = 2" color="primary" label="Voltar" class="q-ml-sm" />
          <q-btn color="primary" label="Marcar consulta" @click="schedule" />
        </q-stepper-navigation>
      </q-step>
    </q-stepper>

    <q-dialog v-model="successDialog" persistent>
      <q-card>
        <q-card-section class="row items-center">
          <q-avatar icon="eva-checkmark-circle-2-outline" color="primary" text-color="white" />
          <span class="q-ml-sm">Agendamento efetuado com sucesso</span>
        </q-card-section>

        <q-card-actions>
          <q-btn flat label="Voltar" color="primary" v-close-popup @click="router.replace({ name: 'Home' })" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script setup>
import { date, useQuasar } from 'quasar';
import router from 'src/router';
import { useAppointmentStore } from 'src/stores/appointment';
import { useUserStore } from 'src/stores/user';
import { computed, onBeforeMount, ref } from 'vue';
import { useRouter } from 'vue-router';

defineProps({
  appointment: {
    type: Number
  }
});

const $q = useQuasar();
const $router = useRouter();
const $userStore = useUserStore();
const $appointmentStore = useAppointmentStore();
const shouldEnableDoctorNextButton = computed(() => form.value.doctor <= 0);
const shouldEnableDateNextButton = computed(() => !form.value.date || !form.value.time);
const columns = [
  {
    name: 'name',
    required: true,
    label: 'Nome',
    align: 'left',
    field: row => row.name,
    sortable: true
  },
  {
    name: 'crm',
    required: true,
    label: 'CRM',
    align: 'left',
    field: row => row.crm
  },
]
const form = ref({
  doctor: [],
  date: null,
  time: null
});
const step = ref(1);
const filterDoctor = ref('');
const pagination = ref({
  rowsPerPage: 0
})
const successDialog = ref(false);

onBeforeMount(() => {
  $appointmentStore.fetchDoctors();
})

async function schedule() {
  const appointmentDate = date.extractDate(`${form.value.date} ${form.value.time}`, "DD/MM/YYYY HH:mm")
  const body = {
    user: $userStore.user.sub,
    doctor: form.value.doctor[0],
    date: appointmentDate
  }
  const { error } = await $appointmentStore.schedule(body);
  if (error) {
    $q.notify({
      color: "error",
      message: "Não foi possível efetuar o agendamento, por favor, tenten novamente mais tarde"
    });
    return;
  }

  $q.dialog({
    title: 'Cosulta marcada',
    message: `Consulta com ${form.value.doctor[0].name} marcada com sucesso`,
    cancel: true,
    persistent: true
  }).onOk(() => {
    $router.replace({ name: 'Home' });
  });
}

function dateOptions(calendarDate) {
  return calendarDate >= date.formatDate(new Date(), 'YYYY/MM/DD');
}
</script>

<style>
.table-doctors {
  height: 400px;

}

.table-doctors>.q-table__top,
thead tr:first-child th {
  background-color: lightgray;
}
</style>
