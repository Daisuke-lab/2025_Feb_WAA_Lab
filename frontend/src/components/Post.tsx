import { Button } from "@mui/material"
import PostType from "../types/PostType"
import Comment from "./Comment"
import { useContext } from "react"
import { PostConetxt } from "./Dashboard"
function Post({id, title, author, comments}:PostType) {
    const setSelectedPostId = useContext(PostConetxt)!.setSelectedPostId
    return (
        <div>
            <div>
            <p>id: {id}</p>
            <p>title: {title}</p>
            <p>author: {author}</p>
            <Button variant="contained" onClick={() => setSelectedPostId(id)}>Select</Button>
            </div>
            <div>
                {comments.map(comment => <Comment {...comment} key={comment.id} />)}
            </div>
        </div>
    )
}
export default Post