import React from 'react';
import { useForm } from "react-hook-form";
import './../css/Adding.css';

function AddingIngredient() {

    const {register, handleSubmit, errors} = useForm();

    const onSubmit = (data) => {

        console.log(data);

        fetch("http://localhost:8080/ingredient", {
            method: 'POST',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
            name: data.name,
            purchasePrice: data.purchasePrice,
            purchaseWeightOrQuantity: data.purchaseOrWeightQuantity,
            oneDeciliterWeight: data.oneDeciliterWeight

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
                <h1>Lägg till ingrediens!</h1>
            </div>

            <form onSubmit={handleSubmit(onSubmit)}>

                <input type="text" placeholder="namn" name="name" ref={register({ required: "Namn på ingrediens krävs"})}/>
                <input type="text" placeholder="inköpspris" name="purchasePrice" ref={register}/>
                <input type="text" placeholder="vikt el antal" name="purchaseOrWeightQuantity" ref={register}/>
                <input type="text" placeholder="vikt i 1dl" name="oneDeciliterWeight" ref={register}/>
                <input type="submit" />

                {errors.name && <p>{errors.name.message}</p>}
            </form>
        </div>

        <div className='float-child'>
            <p>här ska en lista med ingredienser in</p>
        </div>
    </div>

    );
}

export default AddingIngredient;