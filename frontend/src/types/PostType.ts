
import CommentType from "./CommentType"

export default interface PostType {
    id: number,
    title: string,
    content: string,
    author: string,
    comments: CommentType[]
}

export type NewPostType = Omit<PostType, "id" | "comments">