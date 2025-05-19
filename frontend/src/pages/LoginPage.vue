<template>
  <section class="fullscreen text-center q-pa-md">
    <h3>Oncomind</h3>

    <h5>Entrar</h5>

    <q-form @submit="onSubmit" class="q-gutter-md login_form">
      <q-input v-model="email" label="Email" lazy-rules filled
        :rules="[val => val && val.length > 0 || 'Campo email não pode ser vazio']" />

      <q-input filled type="password" v-model="password" label="Senha" lazy-rules :rules="[
        val => val && val.length > 0 || 'Campo senha não pode ser vazio'
      ]" />


      <q-btn label="Entrar" type="submit" color="primary" />
    </q-form>

    <div class="absolute-bottom q-mb-xl">
      <q-btn label="Não tem uma conta? Cadastre-se" :to="{ name: 'Signup' }" flat />
    </div>
  </section>
</template>

<script setup>
import { useAuthStore } from 'src/stores/auth';
import { useQuasar } from 'quasar';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const $q = useQuasar();
const $router = useRouter();
const $authStore = useAuthStore();
const email = ref(null);
const password = ref('senha123');

async function onSubmit() {
  const { error } = await $authStore.login({ email: email.value, password: password.value});
  if (error) {
    $q.notify({
      color: "error",
      message: "Não foi possível efetuar o login"
    });
    return;
  }

  $router.replace({ name: "Home" })
}

</script>

<style>
.login_form {
  margin-inline: auto;
  max-width: 400px;
}
</style>
