import { useState } from "react"
import InputForms from "../ui/InputForms"

import "./Login.css"
import { LoginService } from "../../services/login/login.service"
import { ApiError } from "../../services/errors/ApiError"
import { Login as LoginData } from "../../interfaces/Login"
import { useNavigate } from "react-router-dom"

const Login = () => {
  
  const navigate = useNavigate()
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value)
  }

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value)
  }

  const handleSignin = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault()

    const loginService: LoginService = LoginService.getInstance()

    const data: LoginData | ApiError = await loginService.login({email, password})

    if('token' in data){
      navigate('/workspaces')
    }
  }

  return (
    <div className="login-content">
        <h1 className="title">Login</h1>
        <form className="form-login-content">
            <InputForms key={5} 
              src={import.meta.env.VITE_ICON_EMAIL} 
              placeholder="E-mail" 
              type="text"
              onChange={handleEmailChange}
            />
            <InputForms key={6} 
              src={import.meta.env.VITE_ICON_PASSWORD} 
              placeholder="Password" 
              type="password"
              onChange={handlePasswordChange}
            />
        </form>
        <button 
          className="button-login"
          onClick={handleSignin}
        >
          LOGIN
        </button>
    </div>
  )

}

export default Login