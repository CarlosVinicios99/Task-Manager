import InputForms from "../ui/InputForms"

import "./Signup.css"

const Signup = () => {
  return (
    <div className="signup-content">
        <h1 className="title">Create Your Account</h1>
        <form className="form-content">
            <InputForms key={1} src={import.meta.env.VITE_ICON_NAME} placeholder="Name" type="text"/>
            <InputForms key={2} src={import.meta.env.VITE_ICON_EMAIL} placeholder="E-mail" type="text"/>
            <InputForms key={3} src={import.meta.env.VITE_ICON_PASSWORD} placeholder="Password" type="password"/>
        </form>
        <button className="button-signup">SIGN UP</button>
    </div>
  )
}

export default Signup