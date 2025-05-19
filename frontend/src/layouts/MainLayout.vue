<template>
  <q-layout view="hHh Lpr lFf">
    <q-header>
      <q-toolbar>
        <q-btn
          v-show="title"
          flat
          dense
          round
          icon="eva-chevron-left-outline"
          aria-label="Voltar"
          @click="$router.back()"
        />

        <q-toolbar-title>
          {{ title || 'Oncomind' }}
        </q-toolbar-title>
        <q-space />
        <q-btn flat dense round icon="eva-log-out-outline" aria-label="Sair" @click="logout" />
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { useQuasar } from 'quasar';
import { computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const $q = useQuasar();
const $router = useRouter();
const $route = useRoute();

const title = computed(() => {
  const label = $route.meta.label;
  return label;
})

function logout() {
  $q.dialog({
    title: 'Sair',
    message: 'Você quer mesmo sair?',
    cancel: true,
    persistent: true
  }).onOk(() => {
    sessionStorage.clear();
    $router.replace({ name: 'Login' });
  });
}

</script>
