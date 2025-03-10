import TextField from "@mui/material/TextField";
import Posts from "./Posts";
import { useState } from "react";
import PostType from "../types/PostType";
import { Button } from "@mui/material";

const initialPosts:PostType[] = [
    {id: 1, title: "a", author: "a"},
    {id: 2, title: "b", author: "b"},
    {id: 3, title: "c", author: "c"}
]


export default function Dashboard() {
    const [posts, setPosts] = useState(initialPosts)
    const [firstTitle, setFirstTitle] = useState(posts[0].title)
    const onChange = (e: React.ChangeEvent<HTMLInputElement >) => {
        const newValue = e. currentTarget.value;
        setFirstTitle(newValue)
      }
    const onClick = () => {
        const newPosts = [...posts]
        newPosts[0].title = firstTitle
        setPosts(newPosts)
    }
    return (
        <div>
            <h1>Hello World</h1>
            <TextField id="outlined-basic" label="Outlined" variant="outlined" onChange={onChange} defaultValue={firstTitle}/>
            <Button variant="contained" onClick={onClick}>Change</Button>
            <Posts posts={posts}/>
        </div>
    )
}
