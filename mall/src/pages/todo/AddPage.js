import React from "react";
import { useParams } from "react-router-dom";
import AddComponent from "../../components/todo/AddComponent";

function AddPage(props) {
    return (
        <div className="p-4 w-full bg-white">
            <div className="text text-3xl font-extrabold">
                add page
            </div>
        
            <AddComponent/>
        </div>
    );
}

export default AddPage;