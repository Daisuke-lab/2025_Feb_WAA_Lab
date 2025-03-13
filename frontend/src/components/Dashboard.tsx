import TextField from "@mui/material/TextField";
import Posts from "./Posts";
import { createContext, useContext, useState } from "react";
import PostType from "../types/PostType";
import { Button } from "@mui/material";

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
    
    return (
        <div>
            <PostConetxt.Provider value={postContextValue}>
            <h1>Hello World</h1>
            <Posts/>
            </PostConetxt.Provider>
        </div>
    )
}
