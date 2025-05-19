<template>
  <q-page class="q-pa-lg">
    <h4>Bem-vindo(a), <strong>{{ $authStore.user.name }}</strong></h4>

    <section class="q-mb-lg">
      <q-btn unelevated color="primary" label="Marcar consulta" :to="{ name: 'Appointment' }" />
    </section>

    <section class="flex justify-evenly">
      <AppointmentCard title="Consultas de hoje" :appointments="$userStore.appointmentsToday" />
      <AppointmentCard title="Próximas consultas" :appointments="$userStore.nextAppointments" />
    </section>
  </q-page>
</template>

<script setup>
import { useQuasar } from 'quasar';
import AppointmentCard from 'src/components/AppointmentCard.vue';
import { useAuthStore } from 'src/stores/auth';
import { useUserStore } from 'src/stores/user';
import { onBeforeMount } from 'vue';

const $q = useQuasar();
const $authStore = useAuthStore();
const $userStore = useUserStore();

onBeforeMount(async () => {
  fetchAppointments();
});

async function fetchAppointments() {
  const { error } = await $userStore.fetchAppointments();
  if (error) {
    $q.notify({
      color: "error",
      message: "Não foi possível efetuar o login"
    });
  }
}

</script>
