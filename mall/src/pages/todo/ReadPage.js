import React from "react";
import {  useParams } from "react-router-dom";
import ReadComponent from "../../components/todo/ReadComponent";

function ReadPage(props) {

    const {tno} = useParams();

    return (
        <div className="font-extrabold w-full bg-white mt-6">
            <div className="text text-3xl">
                todo read page {tno}
            </div>

            <ReadComponent tno={tno}/>
        </div>
    );
}

export default ReadPage;