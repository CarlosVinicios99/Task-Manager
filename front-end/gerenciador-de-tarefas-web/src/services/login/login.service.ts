import { Login } from "../../interfaces/Login";
import { ApiError } from "../errors/ApiError";
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

    async login(data: LoginDto): Promise<Login | ApiError>{
        try{
            const url: string = `${import.meta.env.TASK_MANAGER_API_URL}auth/login`
            
            const response: Response = await fetch(url, {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json"
                }
            })

            if(response.status !== 200){
                const responseError: ApiError = await response.json()
                return responseError
            }

            const login: Login = await response.json()

            localStorage.setItem(login.userId, login.token)
            
            return login
        }
        catch(error: unknown){
            return {
                status: 500
            }
        }
    }
}