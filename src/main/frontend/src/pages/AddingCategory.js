import React from 'react';
import { useForm } from "react-hook-form";
import './../css/Adding.css';

function AddingCategory() {

const {register, handleSubmit, errors} = useForm();

    const onSubmit = (data) => {

        console.log(data);

        fetch("http://localhost:8080/foodcategory", {
            method: 'POST',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
            name: data.name
            })
        })
        .then(response => {

            console.log("success");

        })
    }

    return (

    <div className='addingPage' >
        <div className='float-child'>
            <div>
                <h1>Lägg till kategori!</h1>
            </div>

            <form onSubmit={handleSubmit(onSubmit)}>

                <input type="text" placeholder="namn" name="name" ref={register({ required: "Namn på ingrediens krävs"})}/>
                <input type="submit" />

                {errors.name && <p>{errors.name.message}</p>}
            </form>
        </div>

        <div className='float-child'>
            <p> här ska en lista på kategorier in</p>
        </div>
    </div>

    );
}

export default AddingCategory;