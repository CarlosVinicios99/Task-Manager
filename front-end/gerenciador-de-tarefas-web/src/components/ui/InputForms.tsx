import { InputFormsProps } from '../../interfaces/InputFormsProps'

const InputForms = ({src, type, placeholder}: InputFormsProps) => {
  return (
    <div className="input-content">
        <img src={src} alt="ícone" />
        <input type={type ?? "text"} placeholder={placeholder}/>
    </div>
  )
}

export default InputForms