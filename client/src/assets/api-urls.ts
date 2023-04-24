export const API_URLS =  {
    LOGIN: 'users/login',
    SIGNUP: 'users/create',
    EMAIL_VERIFY: (email: string, verificationCode: string): string => `users/verifyEmail/${email}/${verificationCode}`,
    SEARCH_LOCATION: (keyword: string): string => `locations/${keyword}`, 
    LOCATION_DETAIL: (uuid: string): string => `locations/details/${uuid}`,
    COMMENTS_LIST: (uuid: string): string => `comments/byLocations/${uuid}`,
    IMAGE_COMMENTS: (uuid: string): string => `imageComments/byLocations/${uuid}`,
    USER_LOCATION_CREATE: 'user-locations/create',
    IMAGE_POST: 'imageComments/create',
    COMMENTS_CREATE: 'comments/create',
    FILE_UPLOAD: (userId: string) =>  `file/upload?userId=${userId}`,
    GET_USER_IMAGE: (userId: number) => `file/downloadByUserId/${userId}`,
    UPDATE_PROFILE: 'users/update',
    UPDATE_PASSWORD: 'users/updatePassword'
}
