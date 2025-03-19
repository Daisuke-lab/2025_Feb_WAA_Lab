import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter, Route, Routes } from "react-router";
import Dashboard from './components/Dashboard.tsx';
import AddPost from './components/AddPost.tsx';

createRoot(document.getElementById('root')!).render(
    <BrowserRouter>
    <Routes>
    <Route index element={<Dashboard/>}/>
    <Route path="add" element={<AddPost/>}/>
    </Routes>
  </BrowserRouter>
,
)
