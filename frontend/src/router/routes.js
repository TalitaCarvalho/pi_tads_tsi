const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { name: 'Home', path: '', component: () => import('pages/IndexPage.vue') },
      { name: 'Appointment', path: '/consulta', component: () => import('src/pages/AppointmentPage.vue'), meta: { label: 'Nova consulta' } },
    ],
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/auth',
    component: () => import('layouts/AuthLayout.vue'),
    children: [
      { name: 'Login', path: '', component: () => import('src/pages/LoginPage.vue') },
      { name: 'Signup', path: '/signup', component: () => import('src/pages/SignupPage.vue') },
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
