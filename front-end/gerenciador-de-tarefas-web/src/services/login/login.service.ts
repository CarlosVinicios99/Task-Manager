export class LoginService {

    private static instance: LoginService

    private constructor(){}

    static getInstance(): LoginService {
        if(!LoginService.instance){
            LoginService.instance = new LoginService();
        }
        return LoginService.instance
    }

}