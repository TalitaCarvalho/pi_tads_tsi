<template>
  <q-card flat bordered class="appointment_card">
    <q-card-section :class="headerCardBgColor">
      <div class="text-h5">{{ title }}</div>
    </q-card-section>
    <q-separator />

    <q-card-section>
      <section v-show="appointments.length <= 0">
        Nenhuma consulta no momento
      </section>

      <section v-show="appointments.length > 0">
        <q-list separator>
          <q-item v-for="appointment in appointments" :key="appointment.id" flat>
            <q-item-section>
              <p class="text-h6 q-my-none">{{ appointment.doctor.name }}</p>
              <p class="text-subtitle2">{{ appointment.formatted_date }}</p>
            </q-item-section>
            <q-item-section side class="q-px-md">
              <q-btn flat round color="red" icon="eva-close-outline" @click="deleteAppointment(appointment)" />
            </q-item-section>
          </q-item>
        </q-list>
      </section>
    </q-card-section>
  </q-card>
</template>

<script setup>
import { useQuasar } from 'quasar';
import { useAppointmentStore } from 'src/stores/appointment';
import { computed } from 'vue';

const $q = useQuasar();
const $appointmentStore = useAppointmentStore()

const props = defineProps({
  title: {
    type: String,
    required: true
  },
  appointments: {
    type: Array,
    required: true
  }
})

const headerCardBgColor = computed(() => {
  if (props.title === 'Consultas de hoje') {
    return "bg-red-5";
  }

  return "bg-light-blue-4";
})

function deleteAppointment(appointment) {
  $q.dialog({
    title: 'Desmarcar consulta',
    message: `Você quer mesmo desmarcar a consulta com ${appointment.doctor.name}?`,
    cancel: true,
    persistent: true
  }).onOk(async () => {
    $appointmentStore.delete(appointment.id);
    $q.notify({
      position: 'top',
      type: 'positive',
      message: 'Consulta desmarcada com sucesso'
    })
  });
}
</script>

<style>
.appointment_card {
  width: min(420px, 100%);
}
</style>
