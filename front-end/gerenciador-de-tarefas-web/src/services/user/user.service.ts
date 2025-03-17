import { User } from "../../interfaces/User";
import { ApiError } from "../errors/ApiError";
import { CreateUserDto } from "./dto/CreateUserDto";

export class UserService {

    private static instance: UserService

    private constructor(){}

    static getInstance(): UserService {
        if(!UserService.instance){
            UserService.instance = new UserService();
        }
        return UserService.instance
    }

    async createUser(data: CreateUserDto): Promise<User | ApiError>{
        try{
            const url: string = `${import.meta.env.TASK_MANAGER_API_URL}user`
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data),
            })

            if(response.status !== 201){
                const responseError: ApiError = await response.json()
                return responseError
            }

            const user: User = await response.json()    
            return user
        }
        catch(error: any){
            return {
                status: 500,
            }
        }
    }

}