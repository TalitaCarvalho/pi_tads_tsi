import { defineStore } from "pinia";
import { date } from "quasar";
import AppointmentService from "src/service/appointmentService";
import { formatDate } from "src/utils/DateUtils";

export const useUserStore = defineStore('user', {

  state: () => ({
    appointments: []
  }),

  getters: {
    appointmentsToday: (state) => {
      if (state.appointments.length <= 0) return [];
      const today = new Date();
      return state.appointments.filter(appointment => {
        const appointmentDate = new Date(appointment.date);
        const isToday = date.isSameDate(appointmentDate, today, 'day');
        const isSameMonth = date.isSameDate(appointmentDate, today, 'month');
        appointment.formatted_date = formatDate(appointmentDate)
        return isToday && isSameMonth;
      });
    },
    nextAppointments: (state) => {
      if (state.appointments.length <= 0) return [];
      const today = new Date();
      return state.appointments.filter(appointment => {
        const appointmentDate = new Date(appointment.date);
        const isToday = date.isSameDate(appointmentDate, today, 'day');
        const isSameMonth = date.isSameDate(appointmentDate, today, 'month');
        appointment.formatted_date = formatDate(appointmentDate)
        return !(isToday && isSameMonth);
      });
    },
    user: () => {
      const token = sessionStorage.getItem('access_token');
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload));
    }
  },

  actions: {
    async fetchAppointments() {
      try {
        const response = await AppointmentService.userAppointments();
        if (response.status == 200) {
          const result = await response.json();
          this.appointments = result.data
          return {
            success: true
          }
        }

        return {
          error: {
            message: "Não foi possível buscar as consultas"
          }
        }
      } catch (error) {
        return {
          error: error
        }
      }
    },

    removeAppointment(id) {
      const index = this.appointments.findIndex(appointment => appointment.id === id);
      if (index !== -1) {
        this.appointments.splice(index, 1);
        this.appointments = [...this.appointments];
      }
    }
  }
})
