const BASE_URL = `${process.env.API_URL}/v1/medicos`

function fetchDoctors() {
  const accessToken = sessionStorage.getItem('access_token')
  return fetch(`${BASE_URL}`, {
    headers: {
      'Authorization': `Bearer ${accessToken}`
    }
  })
}

export default {
  fetchDoctors
}
