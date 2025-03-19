import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Welcome from './pages/welcome/Welcome'
import Workspaces from './pages/users-workspace/Workspaces'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Welcome />}/>
        <Route path="/welcome" element={<Welcome />}/>
        <Route path="/workspaces" element={<Workspaces/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
