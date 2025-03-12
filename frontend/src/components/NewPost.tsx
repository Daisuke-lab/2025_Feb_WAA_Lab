
import { Button, TextField } from '@mui/material';
import { useForm } from 'react-hook-form';
import { NewPostType } from '../types/PostType';
import axios from 'axios';
import { useRef } from 'react';



export default function NewPost() {
    const titleRef = useRef<HTMLInputElement>(null)
    const contentRef = useRef<HTMLInputElement>(null)
    const authorRef = useRef<HTMLInputElement>(null)
    const onSubmit = async () => {
        const data = {
            title: titleRef.current!.value,
            content: contentRef.current!.value,
            author: contentRef.current!.value
        }
        try {
            const res = await axios.post(`${import.meta.env.VITE_API_ORIGIN}/posts`, data)
        } catch (e) {
            console.error(e)
        }

    }
    return (
        <form onSubmit={onSubmit}>
            <TextField label="Title" variant="outlined" ref={titleRef}/>
            <TextField label="Content" variant="outlined" ref={contentRef}/>
            <TextField label="Author" variant="outlined" ref={authorRef}/>
            <Button variant="contained">Create</Button>
        </form>
    )

}