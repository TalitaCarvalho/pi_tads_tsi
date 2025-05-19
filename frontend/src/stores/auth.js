import { defineStore } from "pinia";
import AuthService from "src/service/authService";

export const useAuthStore = defineStore('auth', {
  getters: {
    user: () => {
      const accessToken = sessionStorage.getItem('access_token');
      if (accessToken) {
        const payload = accessToken.split('.')
        return JSON.parse(atob(payload[1]));
      }
    }
  },

  actions: {
    async login(body) {
      try {
        const response = await AuthService.login(body);
        if (response.status == 200) {
          const result = await response.json();
          sessionStorage.setItem('access_token', result.access_token)
          return {
            success: true
          }
        }
      } catch (error) {
        return {
          error: error
        }
      }
    },
    async signup(body) {
      debugger
      try {
        const response = await AuthService.signup(body);
        if (response.status == 201) {
          return {
            success: true
          }
        }
        return {
          error: "Não foi possível efetuar o cadastro"
        }
      } catch (error) {
        return {
          error: error
        }
      }
    }
  }
})
