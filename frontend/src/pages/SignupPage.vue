<template>
  <section class="fullscreen text-center q-pa-md">
    <h3>Oncomind</h3>

    <h5>Entrar</h5>

    <q-form @submit="onSubmit" class="q-gutter-md login_form">
      <q-input v-model="form.name" label="Nome" lazy-rules filled
        :rules="[val => val && val.length > 0 || 'Campo nome não pode ser vazio']" />

      <q-input v-model="form.email" label="Email" lazy-rules filled
        :rules="['email', val => val && val.length > 0 || 'Campo email não pode ser vazio']" />

      <q-input v-model="form.cpf" label="cpf" lazy-rules filled mask="###.###.###-##"
        :rules="[val => val && val.length > 0 || 'Campo cpf não pode ser vazio']" />

      <q-input v-model="form.password" label="Senha" type="password" lazy-rules filled :rules="[
        val => val && val.length > 0 || 'Campo senha não pode ser vazio'
      ]" />

      <div>
        <q-btn flat label="Voltar" @click="$router.back()" class="q-mr-xl" />
        <q-btn label="Cadastrar" type="submit" color="primary" />
      </div>
    </q-form>
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
const form = ref({
  name: "Marto Francisco",
  cpf: "29024529323",
  email: "martof@email.com",
  password: "senha123"
});

async function onSubmit() {
  const { error } = await $authStore.signup(form.value);
  if (error) {
    $q.notify({
      position: "top",
      color: "danger",
      message: "Não foi possível efetuar o cadastro"
    });
    return;
  }

  $q.notify({
    position: 'top',
    type: 'positive',
    message: 'Cadastro efetuado com sucesso'
  })

  $router.replace({ name: "Login" })
}

</script>

<style>
.login_form {
  margin-inline: auto;
  max-width: 400px;
}
</style>
