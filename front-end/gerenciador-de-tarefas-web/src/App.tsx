import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Welcome from './pages/welcome/Welcome'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Welcome />}/>
        <Route path="/welcome" element={<Welcome />}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
