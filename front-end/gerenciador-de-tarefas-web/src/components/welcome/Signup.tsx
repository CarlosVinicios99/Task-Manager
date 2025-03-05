import InputForms from "../ui/InputForms"

const Signup = () => {
  return (
    <div className="signup-content">
        <h1>Create Your Account</h1>
        <form>
            <InputForms key={1} src="" placeholder="Name" type="text"/>
            <InputForms key={2} src="" placeholder="E-mail" type="text"/>
            <InputForms key={3} src="" placeholder="Password" type="password"/>
        </form>
    </div>
  )
}

export default Signup