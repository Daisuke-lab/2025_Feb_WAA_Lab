import { useEffect, useState } from "react"
import PostType from "../types/PostType"
import Post from "./Post"
import axios from "axios"



export default function Posts() {
    const [posts, setPosts] = useState<PostType[]>([])
    useEffect(() => {
        (async () => {
            try {
                const res = await axios.get(`${import.meta.env.VITE_API_ORIGIN}/posts`)
                setPosts(res.data)
            } catch(e) {
                console.error(e)
            }
            
        })()
    }, [])
    
    return (
        <div>
        {posts.map((post) => <Post {...post} key={post.id}/>)}
        </div>
    )
}