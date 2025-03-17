export interface InputFormsProps {
    src: string,
    type?: string,
    placeholder?: string,
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
}