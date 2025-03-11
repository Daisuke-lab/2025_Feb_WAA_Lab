import TextField from "@mui/material/TextField";
import Posts from "./Posts";
import { useState } from "react";
import PostType from "../types/PostType";
import { Button } from "@mui/material";




export default function Dashboard() {
    return (
        <div>
            <h1>Hello World</h1>
            <Posts/>
        </div>
    )
}
