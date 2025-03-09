import InputForms from "../ui/InputForms"

import "./Login.css"

const Login = () => {
  
  return (
    <div className="login-content">
        <h1 className="title">Login</h1>
        <form className="form-login-content">
            <InputForms key={4} src={import.meta.env.VITE_ICON_EMAIL} placeholder="E-mail" type="text"/>
            <InputForms key={5} src={import.meta.env.VITE_ICON_PASSWORD} placeholder="Password" type="password"/>
        </form>
        <button className="button-login">LOGIN</button>
    </div>
  )

}

export default Login