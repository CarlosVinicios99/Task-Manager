import { useState } from 'react';
import Signup from '../../components/welcome/Signup';
import WelcomeBackSection from '../../components/welcome/WelcomeBackSection';
import './Welcome.css';
import Login from '../../components/welcome/Login';

const Welcome = () => {

  const [isLoginSelected, setIsLoginSelected] = useState<boolean>(false)

  return (
    <div className="welcome-content">
      <WelcomeBackSection selectLogin={setIsLoginSelected}/>
      {isLoginSelected ? (<Login/>) : (<Signup/>)}
    </div>
  )

}
export default Welcome