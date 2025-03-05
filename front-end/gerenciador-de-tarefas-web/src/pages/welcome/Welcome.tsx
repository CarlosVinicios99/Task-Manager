import Signup from '../../components/welcome/Signup';
import WelcomeBackSection from '../../components/welcome/WelcomeBackSection';
import './Welcome.css';

const Welcome = () => {

  return (
    <div className="welcome-content">
      <WelcomeBackSection/>
      <Signup/>
    </div>
  )

}
export default Welcome