export class Constant{

    public static  TOKEN: string= 'token-login';
    public static  REMEMBER_USER: string = 'user_remember';

    public static PATH_LOGIN :string     = 'login';
    public static PATH_SIGNUP :string    = 'signUp' 
    public static PATH_DASHBOARD :string = '/dashboard';
    public static PATH_SECRETS :string   = '/dashboard/secret-password';
    public static PATH_NOTES   :string   = '/dashboard/secret-notes';


    public static emailPattern: any = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

}