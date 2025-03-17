import { useState } from "react"
import InputForms from "../ui/InputForms"

import "./Login.css"

const Login = () => {
  
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value)
  }

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value)
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
        <button className="button-login">LOGIN</button>
    </div>
  )

}

export default Login