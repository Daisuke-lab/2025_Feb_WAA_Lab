import CommentType from "../types/CommentType"

interface Props {
    comment: CommentType
}

export default function Comment({id, name}:CommentType) {
    return (
        <div>
            <p>id: {id}</p>
            <p>name: {name}</p>
        </div>
    )
}