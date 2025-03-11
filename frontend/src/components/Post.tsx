import PostType from "../types/PostType"
import Comment from "./Comment"
function Post({id, title, author, comments}:PostType) {
   
    return (
        <div>
            <div>
            <p>id: {id}</p>
            <p>title: {title}</p>
            <p>author: {author}</p>
            </div>
            <div>
                {comments.map(comment => <Comment {...comment} key={comment.id} />)}
            </div>
        </div>
    )
}
export default Post