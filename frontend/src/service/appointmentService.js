const BASE_URL = `${process.env.API_URL}/v1`

function userAppointments() {
  const accessToken = sessionStorage.getItem('access_token')
  return fetch(`${BASE_URL}/usuarios/agendamentos`, {
    headers: {
      'Authorization': `Bearer ${accessToken}`
    }
  })
}

function schedule(body) {
  const accessToken = sessionStorage.getItem('access_token')
  return fetch(`${BASE_URL}/agendamentos`, {
    method: 'POST',
    body,
    headers: {
      'Authorization': `Bearer ${accessToken}`,
      'Content-Type': 'application/json'
    }
  })
}

function deleteAppointment(id) {
  const accessToken = sessionStorage.getItem('access_token')
  return fetch(`${BASE_URL}/agendamentos/${id}`, {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${accessToken}`
    }
  })
}

export default {
  schedule,
  userAppointments,
  deleteAppointment
}
