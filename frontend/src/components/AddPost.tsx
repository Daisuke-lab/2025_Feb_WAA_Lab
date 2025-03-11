
import { Button, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { NewPostType } from '../types/PostType';
import axios from 'axios';



export default function AddPost() {
    const {
        register,
        handleSubmit,
        formState: { errors },
      } = useForm<NewPostType>();
      const onSubmit = async (data:NewPostType) => {
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_ORIGIN}/posts`, data)
        } catch (e) {
            console.error(e)
        }
      }
    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <TextField label="Title" variant="outlined" {...register("title")}/>
            <TextField label="Content" variant="outlined" {...register("content")}/>
            <TextField label="Author" variant="outlined" {...register("author")}/>
            <Button variant="contained">Create</Button>
        </form>
    )

}