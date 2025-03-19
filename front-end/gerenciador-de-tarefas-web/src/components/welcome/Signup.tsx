import { useState } from "react"
import InputForms from "../ui/InputForms"

import "./Signup.css"
import { UserService } from "../../services/user/user.service"
import { User } from "../../interfaces/User"
import { ApiError } from "../../services/errors/ApiError"
import { useNavigate } from "react-router-dom"
import { LoginService } from "../../services/login/login.service"

const Signup = () => {

  const navigate = useNavigate();

  const [name, setName] = useState<string>("")
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")
  const [confirmPassword, setConfirmPassword] = useState<string>("")


  const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value)
  }

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value)
  }

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value)
  }

  const handleConfirmPasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setConfirmPassword(e.target.value)
  }

  const handleSignup = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault()

    const userService: UserService = UserService.getInstance()

    const data: User | ApiError = await userService.createUser({name, email, password, confirmPassword})

    if('id' in data){
      const user: User = data
      const loginService: LoginService = LoginService.getInstance()
      await loginService.login({email: user.email, password})
      navigate('/workspaces')
    }

    if('status' in data){
      const apiError: ApiError = data
      switch(apiError.status){
        case 400:
          break
        case 409:
          break
        case 500:
          break
      }
    }
  }

  return (
    <div className="signup-content">
        <h1 className="title">Create Your Account</h1>
        <form className="form-content">
            <InputForms key={1} 
              src={import.meta.env.VITE_ICON_NAME} 
              placeholder="Name" 
              type="text"
              onChange={handleNameChange}
            />
            <InputForms 
              key={2} 
              src={import.meta.env.VITE_ICON_EMAIL} 
              placeholder="E-mail" 
              type="text"
              onChange={handleEmailChange}
            />
            <InputForms 
              key={3} 
              src={import.meta.env.VITE_ICON_PASSWORD} 
              placeholder="Password" 
              type="password"
              onChange={handlePasswordChange}
            />
            <InputForms 
              key={4} 
              src={import.meta.env.VITE_ICON_PASSWORD} 
              placeholder="Password" 
              type="password"
              onChange={handleConfirmPasswordChange}
            />
        </form>
        <button 
          className="button-signup"
          onClick={handleSignup}
        >
          SIGN UP
        </button>
    </div>
  )
}

export default Signup