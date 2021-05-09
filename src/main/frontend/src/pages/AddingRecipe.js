import React from 'react';
import { useForm } from "react-hook-form";
import './../css/Adding.css';

function AddingRecipe() {

   const {register, handleSubmit, errors} = useForm();

       const onSubmit = (data) => {

           console.log(data);

           fetch("http://localhost:8080/recipe", {
               method: 'POST',
               headers: {
                   'Accept': 'application/json, text/plain, */*',
                   'Content-Type': 'application/json'
               },
               body: JSON.stringify({
               title: data.title,
               portionsOrAmount: data.portionsOrAmount,
               foodCategoryId: data.foodCategoryId,
               recipeIngredients: data.recipeIngredients

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

                   <input type="text" placeholder="titel" name="title" ref={register({ required: "Namn på recept krävs"})}/>
                   <input type="text" placeholder="portioner el antal" name="portionsOrAmount" ref={register}/>
                   <input type="text" placeholder="instruktioner" name="instructions" ref={register}/>
                   <input type="text" placeholder="kategori" name="foodCategoryId" ref={register}/>
                   <input type="text" placeholder="set med ingredienser" name="recipeIngredients" ref={register}/>
                   <input type="submit" />

                   {errors.name && <p>{errors.name.message}</p>}
               </form>
           </div>

           <div className='float-child'>
               <p>här ska en lista med recept in</p>
           </div>
       </div>

       );
}

export default AddingRecipe;