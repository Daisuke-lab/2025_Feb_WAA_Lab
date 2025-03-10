import PostType from "../types/PostType"

function Post({id, title, author}:PostType) {
   
    return (
        <div>
        <p>id: {id}</p>
        <p>title: {title}</p>
        <p>author: {author}</p>
        </div>
    )
}
export default Post