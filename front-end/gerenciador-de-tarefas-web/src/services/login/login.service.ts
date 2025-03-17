import { LoginDto } from "./dto/LoginDto";

export class LoginService {

    private static instance: LoginService

    private constructor(){}

    static getInstance(): LoginService {
        if(!LoginService.instance){
            LoginService.instance = new LoginService();
        }
        return LoginService.instance
    }

    async login(data: LoginDto){
        try{
            const url: string = `${import.meta.env.TASK_MANAGER_API_URL}auth/login`
            
            const response = await fetch(url, {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json"
                }
            })
            const loginResponse = await response.json()
            return loginResponse
        }
        catch(error){
            return null
        }
    }
}