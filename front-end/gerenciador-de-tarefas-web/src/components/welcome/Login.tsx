import InputForms from "../ui/InputForms"

const Login = () => {
  
  return (
    <div className="login-content">
        <h1>Login</h1>
        <p>Enter your credentials</p>
        <InputForms key={4} src={import.meta.env.VITE_ICON_EMAIL} placeholder="E-mail" type="text"/>
        <InputForms key={5} src={import.meta.env.VITE_ICON_PASSWORD} placeholder="Password" type="password"/>
        <button>Login</button>
    </div>
  )

}

export default Login