import { WelcomeBackSectionProps } from "../../interfaces/WelcomeBackSectionProps"
import "./WelcomeBackSection.css"

const WelcomeBackSection = ({selectLogin}: WelcomeBackSectionProps) => {
  return (
    <div className='welcome-back-content'>
        <h1>Welcome Back!</h1>
        <div className="text-content">
          <p>To keep connected with us</p>
          <p>please login with your personal info</p>
        </div>
        <button className="signin-button" onClick={() => selectLogin(true)}>SIGN IN</button>
    </div>
  )
}

export default WelcomeBackSection