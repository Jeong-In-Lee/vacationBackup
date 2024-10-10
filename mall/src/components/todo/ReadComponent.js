import React, { useEffect, useState } from "react";
import { getOne } from "../../api/todoApi";
import useCustomMove from "../../hooks/useCustomMove";

const initState = {
    tno : 0,
    title:'',
    content:'',
    dueDate:'',
    complete:false
}

function ReadComponent({tno}) {

    const [todo, setTodo] = useState(initState);

    const {moveToList, moveToModify} = useCustomMove();

    //번호가 바뀔 때만 랜더링
    useEffect(() => {
        getOne(tno).then(data=>{
            console.log(data);
            setTodo(data);
        })
    }, [tno]);

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4 ">
            {makeDiv("Tno", todo.tno)}
            {makeDiv("title", todo.title)}
            {makeDiv("content", todo.content)}
            {makeDiv("due date", todo.dueDate)}
            {makeDiv("complete", todo.complete ? 'Completed' : 'Not yet')}
        
            <div className="flex justify-end p-4">
                <button type="button"
                        className="text-sky-400 bg-transparent border border-solid border-sky-400 hover:bg-sky-400 hover:text-white active:bg-sky-600 font-bold uppercase px-8 py-3 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                        onClick={()=>moveToList()}
                >
                    List
                </button>

                <button type="button"
                        className="text-red-400 bg-transparent border border-solid border-red-400 hover:bg-red-400 hover:text-white active:bg-red-600 font-bold uppercase px-8 py-3 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                        onClick={()=>moveToModify(todo.tno)}
                >
                    Modify
                </button>
            </div>
        
        </div>
        
    );
}

const makeDiv = (title,  value) => 
    <div className="flex justify-center">
        <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-1/5 p-6 text-right font-bold">{title}</div>
            <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
                {value}
            </div>
        </div>
    </div>

export default ReadComponent;