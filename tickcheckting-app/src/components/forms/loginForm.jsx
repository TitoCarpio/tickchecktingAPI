import React from 'react';
import Input from '../inputs/textInput';
import { useForm } from 'react-hook-form';
import SubmitButton from '../buttons/submitButton';

const LoginForm = ({ onSubmitHandler }) => {
    const {
      register,
      handleSubmit,
      formState: { errors }
    } = useForm();
  
    const onSubmit = data => {
      onSubmitHandler(data);
      //reset();
    };
  
      return (
          <form onSubmit={handleSubmit(onSubmit)} className='flex flex-col gap-2 mx-24'>
          <Input
            id='username'
            label={'Email'}
            classNameLabel={"text-p-brown"}
            placeholder='Username'
            classNameDiv='w-full'
            error={errors?.username?.message}
            className='text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange'
            register={{
              ...register('username', {
                required: 'Username required',
              }),
            }}
          />
          <Input
            id='password'
            label={'Password'}
            classNameLabel={"text-p-brown"}
            placeholder='Password'
            classNameDiv='w-full'
            className='text-primary-500  pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange'
            error={errors?.password?.message}
            register={{
              ...register('password', {
                required: 'Password required',
              }),
            }}
            type='password'
          />
          <div className='flex justify-end '>
            <SubmitButton text='Sign In' />
          </div>
        </form>
      );
  };
  
  export default LoginForm;