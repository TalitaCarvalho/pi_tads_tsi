import { defineStore } from "pinia";
import DoctorService from "src/service/doctorService";
import AppointmentService from "src/service/appointmentService";
import { useUserStore } from "./user";

export const useAppointmentStore = defineStore('appointment', {
  state: () => ({
    doctors: []
  }),


  actions: {
    async fetchDoctors() {
      try {
        const response = await DoctorService.fetchDoctors();
        if (response.status == 200) {
          const result = await response.json();
          this.doctors = result.data
          return {
            success: true
          }
        }

        return {
          error: {
            message: "Não foi possível listar os médicos disponíveis"
          }
        }
      } catch (error) {
        return {
          error: error
        }
      }
    },

    async schedule({ user, doctor, date }) {
      const body = {
        user,
        treatment: "consulta online",
        date: date.toISOString(),
        doctor: doctor.id,
        clinic: 1
      }
      try {
        const response = await AppointmentService.schedule(JSON.stringify(body));
        if (response.status == 201) {
          const result = await response.json();
          return {
            data: result
          }
        }
        return {
          error: {
            message: "Não foi possível listar os médicos disponíveis"
          }
        }
      } catch (error) {
        return {
          error: error
        }
      }
    },

    async delete(id) {
      try {
        const response = await AppointmentService.deleteAppointment(id);
        if (response.status === 200) {
          const userStore = useUserStore();
          userStore.removeAppointment(id);
          return {
            success: true
          }
        }
        return {
          error: {
            message: "Não foi possível deletar o agendamento"
          }
        }
      } catch (error) {
        return {
          error: error
        }
      }
    },
  }
});
