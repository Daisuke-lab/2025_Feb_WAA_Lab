import TextField from "@mui/material/TextField";
import Posts from "./Posts";
import { createContext, useContext, useState } from "react";
import PostType from "../types/PostType";
import { Button } from "@mui/material";
import AddPost from "./AddPost";
import { useNavigate } from "react-router";

interface PostContextType {
    selectedPostId: number,
    setSelectedPostId: React.Dispatch<React.SetStateAction<number>>
}

export const PostConetxt = createContext<PostContextType | null>(null)

export default function Dashboard() {
    const [selectedPostId, setSelectedPostId] = useState(0)
    const postContextValue = {
        selectedPostId,
        setSelectedPostId
    }
    const navigate = useNavigate()
    return (
        <div>
            <PostConetxt.Provider value={postContextValue}>
            <h1>Hello World</h1>
            
            <Button onClick={() => navigate("add")}>Add Post</Button>
            <Posts/>
            </PostConetxt.Provider>
        </div>
    )
}
