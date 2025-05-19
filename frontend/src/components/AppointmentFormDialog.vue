<template>
  <q-dialog ref="dialogRef" @hide="onDialogHide" transition-show="slide-up" transition-hide="slide-down" persistent
    maximized>
    <q-card>
      <q-bar>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-white text-primary">Close</q-tooltip>
        </q-btn>
      </q-bar>

      <q-card-section>
        <h4>Nova Consulta</h4>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-form @submit="onSubmit" class="q-gutter-md login_form">
          <q-select filled v-model="form.doctor" use-input input-debounce="0" label="Selecionar médico"
            :options="doctorListFiltered" @filter="filter" style="width: 250px" behavior="menu">
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey">
                  No results
                </q-item-section>
              </q-item>
            </template>
          </q-select>

          <q-input v-model="form.date" filled type="password" label="Senha" lazy-rules :rules="[
            val => val && val.length > 0 || 'Campo senha não pode ser vazio'
          ]" />


          <q-btn label="Entrar" type="submit" color="primary" />
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { useDialogPluginComponent } from 'quasar';
import { ref } from 'vue';

defineProps({
  appointment: {
    type: Object
  }
})

defineEmits([
  ...useDialogPluginComponent.emits
])
const { dialogRef, onDialogHide } = useDialogPluginComponent()

const form = ref({})
const doctorList = [
  'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
]
const doctorListFiltered = ref([
  'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
])


function onSubmit() {
  console.log("submit form")
}


function filter(val, update) {
  if (doctorListFiltered.value !== null) {
    update(() => {
      doctorListFiltered.value = doctorList
    })
    return
  }

  setTimeout(() => {
    update(() => {
      const needle = val.toLowerCase()
      doctorListFiltered.value = doctorList.filter(v => v.toLowerCase().indexOf(needle) > -1)
    })
  }, 2000)
}
</script>
