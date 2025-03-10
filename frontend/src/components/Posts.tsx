import PostType from "../types/PostType"
import Post from "./Post"

interface Props {
    posts: PostType[]
}


export default function Posts({posts}:Props) {
    console.log(posts)
    
    return (
        <div>
        {posts.map((post) => <Post {...post} key={post.id}/>)}
        </div>
    )
}