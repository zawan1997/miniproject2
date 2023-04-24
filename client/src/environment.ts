import { API_URLS } from "./api-urls";

export const environment = {
  name: 'local',
  production: false,
  APP: {
    BASE_URL: 'http://localhost:8080/api/v1/',
    // BASE_URL: '/api/v1/',
    DOMAIN_URL: 'http://localhost:4200',
    API_URLS
  }
};
