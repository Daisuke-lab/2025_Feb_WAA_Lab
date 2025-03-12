import TextField from "@mui/material/TextField";
import Posts from "./Posts";
import { createContext, useContext, useState } from "react";
import PostType from "../types/PostType";
import { Button } from "@mui/material";


export const PostConetxt = createContext(0)

export default function Dashboard() {
    return (
        <div>
            <PostConetxt.Provider value={0}>
            <h1>Hello World</h1>
            <Posts/>
            </PostConetxt.Provider>
        </div>
    )
}
