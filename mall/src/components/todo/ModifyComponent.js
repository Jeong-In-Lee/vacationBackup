import React, { useEffect, useState } from "react";
import ResultModal from "../common/ResultModal";
import useCustomMove from "../../hooks/useCustomMove";
import { deleteOne, getOne, putModify, putOne } from "../../api/todoApi";


const initState = {
    tno:0,
    title:'',
    content: '',
    dueDate:'',
    complete : false
}

function ModifyComponent({tno}) {
    const [todo, setTodo] = useState({...initState});
    const [result, setResult] = useState(null);
    const {moveToRead ,moveToList} = useCustomMove();

    useEffect(() => {
        getOne(tno).then(data=>{
            console.log(data);
            setTodo(data);
        })
    }, [tno]);


    const handleChangeTodo = (e) => {
        console.log(e.target.name, e.target.value);

        todo[e.target.name] = e.target.value;

        setTodo({...todo});
    }

    const handleChangeTodoComplete = (e) => {
        const value = e.target.value;
        todo.complete = (value==='Y');
        setTodo({...todo});

    }

    const handleClickDelete = (e) => {
        deleteOne(tno).then(data => {
            console.log("detlete result : " + data);
            setResult('Deleted');
        })
    }

    const handleClickModify = (e) => {
        putOne(todo).then(data => {
            console.log("detlete result : " + data);
            setResult('Modified');
        })
    }

    const closeModal = (e) => {
        setResult(null);
        if (result === 'Deleted'){
            moveToList();
        }
        else{
            moveToRead(tno);
        }
    }


    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">

            <div className="flex justify-center mt-10">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">TNO</div>
                    <div className="w-4/5 p-6 rounded-r border border-solid shadow-md bg-gray-100">
                        {todo.tno}        
                    </div>  
                </div>
            </div>

            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">TTILE</div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                        name="title"
                        type={'text'} 
                        value={todo.title}
                        onChange={handleChangeTodo}
                    >
                    </input>

                </div>
            </div>

            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">CONTENT</div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                        name="content"
                        type={'text'} 
                        value={todo.content}
                        onChange={handleChangeTodo}
                    >
                    </input>
                </div>  
            </div>

            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">DUEDATE</div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-300 shadow-md" 
                        name="dueDate"
                        type={'date'} 
                        value={todo.dueDate}
                        onChange={handleChangeTodo}
                    >
                    </input>
                </div>
            </div>

            <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                <div className="w-1/5 p-6 text-right font-bold">COMPLETE</div>
                <select
                    name="status"
                    className="border-solid border-2 rounded m-1 p-2"
                    onChange={handleChangeTodoComplete}
                    value = {todo.complete ? 'Y':'N'}
                >
                    <option value='Y'>Complete</option>
                    <option value='N'>Not Yet</option>
                </select>
            </div>
            
            <div className="flex justify-end p-4">
                <button 
                    type="button" 
                    className="text-red-400 bg-transparent border border-solid border-red-400 hover:bg-red-400 hover:text-white active:bg-red-600 font-bold uppercase px-8 py-3 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                    onClick={handleClickDelete}    
                >
                    Delete
                </button>

                <button 
                    type="button" 
                    className="text-sky-400 bg-transparent border border-solid border-sky-400 hover:bg-sky-400 hover:text-white active:bg-sky-600 font-bold uppercase px-8 py-3 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                    onClick={handleClickModify}
                >
                    Modify
                </button>
            </div>

            {result ? <ResultModal title={'처리결과'} content = {result} callbackFn={closeModal}></ResultModal> : <></>}
        </div>
    );

}

export default ModifyComponent;